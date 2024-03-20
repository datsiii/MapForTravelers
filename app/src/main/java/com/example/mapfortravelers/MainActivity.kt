package com.example.mapfortravelers

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.PointF
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.mapfortravelers.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.GeoObjectTapEvent
import com.yandex.mapkit.layers.GeoObjectTapListener
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.GeoObjectSelectionMetadata
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.MapWindow
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.RotationType
import com.yandex.mapkit.map.VisibleRegionUtils
import com.yandex.mapkit.search.Response
import com.yandex.mapkit.search.SearchFactory
import com.yandex.mapkit.search.SearchManager
import com.yandex.mapkit.search.SearchManagerType
import com.yandex.mapkit.search.SearchOptions
import com.yandex.mapkit.search.Session
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.Error
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.network.NetworkError
import com.yandex.runtime.network.RemoteError
import java.util.Random


class MainActivity : AppCompatActivity(), /*UserLocationObjectListener,*/ Session.SearchListener, CameraListener {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val startLocation = Point(55.669986, 37.480409)
    private var zoomValue: Float = 16.5f
    private lateinit var mapObjectCollection: MapObjectCollection
    private lateinit var placemarkMapObject: PlacemarkMapObject
    private lateinit var map: Map
    private lateinit var mapWindow: MapWindow
    //private lateinit var locationMapKit: UserLocationLayer
    private lateinit var searchEdit: EditText
    private lateinit var searchManager: SearchManager
    private lateinit var searchSession: Session

    private fun submitQuery(query:String){
        searchSession = searchManager.submit(query, VisibleRegionUtils.toPolygon(binding.appBarMain.content.mapview.map.visibleRegion), SearchOptions(), this)
    }


    private val geoObjectTapListener = object : GeoObjectTapListener {
        override fun onObjectTap(geoObjectTapEvent: GeoObjectTapEvent): Boolean {
            val selectionMetadata: GeoObjectSelectionMetadata = geoObjectTapEvent
                .geoObject
                .metadataContainer
                .getItem(GeoObjectSelectionMetadata::class.java)
            binding.appBarMain.content.mapview.map.selectGeoObject(selectionMetadata)
            return false
        }
    }

    private val inputListener = object : InputListener {
        override fun onMapLongTap(map: com.yandex.mapkit.map.Map, point: Point) {
            // передвижение метки при долгом нажатии
            //placemarkMapObject.geometry = point
            // создание диалога при долгом нажатии
            var flag = false
            val dialog = DialogPMarkFragment.newInstance(text = "Описание", hint = "Description", isMultiline = true)
            dialog.onOk = {
                val text = dialog.editText.text
                // do something
                flag = true
                createPlacemark(point)
            }
            dialog.onCancel = {
                flag = false
            }
            dialog.show(supportFragmentManager, "editDescription")
            if (flag){
                // создание метки при нажатии кнопки ok
                createPlacemark(point)
            }

        }

        override fun onMapTap(map: com.yandex.mapkit.map.Map, point: Point) {
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setApiKey(savedInstanceState) // Проверяем: был ли уже ранее установлен API-ключ в приложении. Если нет - устанавливаем его.
        MapKitFactory.initialize(this) // Инициализация библиотеки для загрузки необходимых нативных библиотек.
        binding =
            ActivityMainBinding.inflate(layoutInflater) // Раздуваем макет только после того, как установили API-ключ
        setContentView(binding.root) // Размещаем пользовательский интерфейс в экране активности

        mapWindow = binding.appBarMain.content.mapview.mapWindow
        map = mapWindow.map
        //mapview = binding.appBarMain.content.mapview

        moveToStartLocation()
        setMarkerInStartLocation()
        binding.appBarMain.content.mapview.map.addTapListener(geoObjectTapListener) // Добавляем слушатель тапов по объектам
        map.addInputListener(inputListener) // Добавляем слушатель


        val navView: NavigationView = findViewById(R.id.nav_view) // шторка
        val navController = findNavController(R.id.nav_host_fragment_content_main)  // находим NavController

        navView.setupWithNavController(navController) // устанавливается NavController для шторки

        var mapKit: MapKit = MapKitFactory.getInstance()
        requestLocationPermission()
        /*locationMapKit = mapKit.createUserLocationLayer(binding.appBarMain.content.mapview.mapWindow)
        locationMapKit.isVisible = true
        locationMapKit.setObjectListener(this)*/
        SearchFactory.initialize(this)
        searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED)
        binding.appBarMain.content.mapview.map.addCameraListener(this)
        searchEdit = findViewById(R.id.search_edit)
        searchEdit.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                submitQuery(searchEdit.text.toString())
            }
            false
        }

    }

    private fun requestLocationPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),100)
                return
        }
    }
    private fun moveToStartLocation() {
        binding.appBarMain.content.mapview.map.move(
            CameraPosition(startLocation, zoomValue, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5f),
            null
        )
    }

    private fun setMarkerInStartLocation() {
        val marker = R.drawable.ic_ya_pin // Добавляем ссылку на картинку
        mapObjectCollection =
            binding.appBarMain.content.mapview.map.mapObjects // Инициализируем коллекцию различных объектов на карте
        placemarkMapObject = mapObjectCollection.addPlacemark(
            startLocation,
            ImageProvider.fromResource(this, marker)
        ) // Добавляем метку со значком
        placemarkMapObject.opacity = 0.5f // Устанавливаем прозрачность метке
        placemarkMapObject.setText("Обязательно к посещению!") // Устанавливаем текст сверху метки
    }


    private fun setApiKey(savedInstanceState: Bundle?) {
        val haveApiKey = savedInstanceState?.getBoolean("haveApiKey")
            ?: false // При первом запуске приложения всегда false
        if (!haveApiKey) {
            MapKitFactory.setApiKey(MAPKIT_API_KEY) // API-ключ должен быть задан единожды перед инициализацией MapKitFactory
        }
    }

    // Если Activity уничтожается (например, при нехватке памяти или при повороте экрана) - сохраняем информацию, что API-ключ уже был получен ранее
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("haveApiKey", true)
    }

    // Отображаем карты перед моментом, когда активити с картой станет видимой пользователю:
    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.appBarMain.content.mapview.onStart()
    }

    // Останавливаем обработку карты, когда активити с картой становится невидимым для пользователя:
    override fun onStop() {
        binding.appBarMain.content.mapview.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    private var placemarkTapListener: MapObjectTapListener =
        object: MapObjectTapListener{
            override fun onMapObjectTap(mapObject: MapObject, point: Point): Boolean {
                if (mapObject is PlacemarkMapObject) {
                    val placemark = mapObject
                    val randomRadius: Float = 100.0f + 50.0f * Random().nextFloat()
                    val curGeometry = placemark.geometry
                    //val newGeometry = Circle(curGeometry.center, randomRadius)
                    //circle.geometry = newGeometry
                    /*val userData = placemark.userData
                    if (userData is PlacemarkMapObjectUserData) {
                        val placemarkUserData: PlacemarkMapObjectUserData =
                            userData as PlacemarkMapObjectUserData
                        val toast = Toast.makeText(
                            applicationContext,
                            ("Placemark with id " + placemarkUserData.id + " and description '"
                                    + placemarkUserData.description) + "' tapped",
                            Toast.LENGTH_SHORT
                        )
                        toast.show()
                    }*/
                    val toast = Toast.makeText(
                        applicationContext,
                        ("Placemark tapped"),
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                    val intent = Intent(this@MainActivity, PMarkActivity::class.java)
                    startActivity(intent)
                }
                return true
            }
        }

    private class PlacemarkMapObjectUserData internal constructor(val id: Int, val description: String)

    private fun createPlacemark(point: Point) {
        placemarkMapObject = map.mapObjects.addPlacemark(
            point,
            ImageProvider.fromResource(this@MainActivity, R.drawable.ic_ya_pin),
            IconStyle().apply { anchor = PointF(0.5f, 1.0f) }
        ).apply {
            isDraggable = true
        }
        placemarkMapObject.addTapListener(placemarkTapListener)
    }


    companion object {
        const val MAPKIT_API_KEY = "6ef8d0c5-2284-480e-aac8-1282e21b2c6b"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }
    fun onNavigationItemSelected(item: MenuItem):Boolean {
        val id = item.getItemId()
        if (id == R.id.nav_home)
        {
            // Здесь обработка клика на первом пункте меню
        }
        else if (id == R.id.nav_gallery)
        {
            // Здесь обработка клика на втором пункте меню
        }
        else if (id == R.id.nav_slideshow)
        {
            // Здесь обработка клика на третьем пункте меню
        }
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    /*override fun onObjectAdded(userLocationView: UserLocationView) {
        locationMapKit.setAnchor(
            PointF((binding.appBarMain.content.mapview.width *0.5).toFloat(),(binding.appBarMain.content.mapview.height*0.5).toFloat()),
            PointF((binding.appBarMain.content.mapview.width *0.5).toFloat(),(binding.appBarMain.content.mapview.height*0.83).toFloat())
        )
        userLocationView.arrow.setIcon(ImageProvider.fromResource(this, R.drawable.ic_user_arrow))
        val picIcon = userLocationView.pin.useCompositeIcon()
        picIcon.setIcon("icon", ImageProvider.fromResource(this, R.drawable.search_result), IconStyle().
        setAnchor(PointF(0f, 0f))
            .setRotationType(RotationType.ROTATE).setZIndex(0f).setScale(1f)
        )
        picIcon.setIcon("pin", ImageProvider.fromResource(this, R.drawable.nothing),
            IconStyle().setAnchor(PointF(0.5f, 0.5f)).setRotationType(RotationType.ROTATE).setZIndex(1f).setScale(0.5f)
        )
        userLocationView.accuracyCircle.fillColor = Color.BLUE and -0x66000001
    }*/

    /*override fun onObjectRemoved(p0: UserLocationView) {

    }

    override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {

    }*/

    override fun onSearchResponse(response: Response) {
        val mapObjects: MapObjectCollection = binding.appBarMain.content.mapview.map.mapObjects
        mapObjects.clear()
        for(searchResult in response.collection.children){
            val resultLocation = searchResult.obj!!.geometry[0].point!!
            /*if (response != null){
                mapObjects.addPlacemark(resultLocation, ImageProvider.fromResource(this, R.drawable.ic_ya_pin))
            }*/
        }
    }

    override fun onSearchError(error: Error) {
        var errorMessage = "Неизвестная ошибка"
        if (error is RemoteError){
            errorMessage = "Беспроводная ошибка"
        }
        else if(error is NetworkError){
            errorMessage = "Проблема с интернетом"
        }
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onCameraPositionChanged(
        map: Map,
        cameraPosition: CameraPosition,
        cameraUpdateReason: CameraUpdateReason,
        finished: Boolean
    ) {
        if(finished){
            submitQuery(searchEdit.text.toString())
        }
    }
}