package com.example.mapfortravelers

import android.graphics.PointF
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.mapfortravelers.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.GeoObjectTapEvent
import com.yandex.mapkit.layers.GeoObjectTapListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.GeoObjectSelectionMetadata
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.MapWindow
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.runtime.image.ImageProvider


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val startLocation = Point(55.669986, 37.480409)
    private var zoomValue: Float = 16.5f
    private lateinit var mapObjectCollection: MapObjectCollection
    private lateinit var placemarkMapObject: PlacemarkMapObject
    private lateinit var map: Map
    private lateinit var mapWindow: MapWindow
    private lateinit var mapObjects: MapObjectCollection

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

        moveToStartLocation()
        setMarkerInStartLocation()
        binding.appBarMain.content.mapview.map.addTapListener(geoObjectTapListener) // Добавляем слушатель тапов по объектам
        map.addInputListener(inputListener) // Добавляем слушатель


        val navView: NavigationView = findViewById(R.id.nav_view) // шторка
        val navController = findNavController(R.id.nav_host_fragment_content_main)  // находим NavController

        navView.setupWithNavController(navController) // устанавливается NavController для шторки
    }

    private fun moveToStartLocation() {
        binding.appBarMain.content.mapview.map.move(
            CameraPosition(startLocation, zoomValue, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5f),
            null
        )
    }

    private fun setMarkerInStartLocation() {
        val marker = R.drawable.ic_pin // Добавляем ссылку на картинку
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

    private fun createPlacemark(point: Point) {
        placemarkMapObject = map.mapObjects.addPlacemark(
            point,
            ImageProvider.fromResource(this@MainActivity, R.drawable.ic_pin),
            IconStyle().apply { anchor = PointF(0.5f, 1.0f) }
        ).apply {
            isDraggable = true
        }
    }

    private fun createPlacemarkMapObjectWithViewProvider() {
        var textView = TextView(this);

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
}