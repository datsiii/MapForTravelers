package com.example.mapfortravelers

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.mapfortravelers.databinding.ActivityMainBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.GeoObjectTapEvent
import com.yandex.mapkit.layers.GeoObjectTapListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.GeoObjectSelectionMetadata
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.runtime.image.ImageProvider


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val startLocation = Point(55.669986, 37.480409)
    private var zoomValue: Float = 16.5f
    private lateinit var mapObjectCollection: MapObjectCollection
    private lateinit var placemarkMapObject: PlacemarkMapObject

    private val geoObjectTapListener = object : GeoObjectTapListener {
        override fun onObjectTap(geoObjectTapEvent: GeoObjectTapEvent): Boolean {
            val selectionMetadata: GeoObjectSelectionMetadata = geoObjectTapEvent
                .geoObject
                .metadataContainer
                .getItem(GeoObjectSelectionMetadata::class.java)
            binding.content.mapview.map.selectGeoObject(selectionMetadata)
            return false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setApiKey(savedInstanceState) // Проверяем: был ли уже ранее установлен API-ключ в приложении. Если нет - устанавливаем его.
        MapKitFactory.initialize(this) // Инициализация библиотеки для загрузки необходимых нативных библиотек.
        binding = ActivityMainBinding.inflate(layoutInflater) // Раздуваем макет только после того, как установили API-ключ
        setContentView(binding.root) // Размещаем пользовательский интерфейс в экране активности
        moveToStartLocation()
        setMarkerInStartLocation()
        binding.content.mapview.map.addTapListener(geoObjectTapListener) // Добавляем слушатель тапов по объектам
    }

    private fun moveToStartLocation() {
        binding.content.mapview.map.move(
            CameraPosition(startLocation, zoomValue, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5f),
            null)
    }

    private fun setMarkerInStartLocation() {
        val marker = R.drawable.ic_pin // Добавляем ссылку на картинку
        mapObjectCollection = binding.content.mapview.map.mapObjects // Инициализируем коллекцию различных объектов на карте
        placemarkMapObject = mapObjectCollection.addPlacemark(startLocation, ImageProvider.fromResource(this, marker)) // Добавляем метку со значком
        placemarkMapObject.opacity = 0.5f // Устанавливаем прозрачность метке
        //placemarkMapObject.setText("Обязательно к посещению!") // Устанавливаем текст сверху метки
    }


    private fun setApiKey(savedInstanceState: Bundle?) {
        val haveApiKey = savedInstanceState?.getBoolean("haveApiKey") ?: false // При первом запуске приложения всегда false
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
        binding.content.mapview.onStart()
    }

    // Останавливаем обработку карты, когда активити с картой становится невидимым для пользователя:
    override fun onStop() {
        binding.content.mapview.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
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
}