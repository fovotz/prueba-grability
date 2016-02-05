Código fuente https://github.com/fovotz/prueba-grability.git

Aplicativo desplegado http://grability-mandev.rhcloud.com/prueba/


Coding Challenge
Para este punto se desarrollo una aplicación web teniendo en cuenta lo sigiente:

Backend:
- Spring Boot Starter Web (Incluye una colección de frameworks de Spring, entre ellos Spring Boot y Spring Web)

Para el backend de la aplicación se usó aun arquitectura por capas definidas así.

- Servicio: Clases presentes en el paquete (com.hackerrank.controller)
- Presentación: Clases presentes en el paquete (com.hackerrank.controller)

Descripción de clases.

MatrixService: Clase que contiene la implementación de la lógica referente a la matrix y las operaciones que se pueden hacer sobre esta.

HackerrankController: Clase encargada de definir los métodos de punto de entrada a las petición http realizadas por el cliente

Clases Varias: En el proyecto existen clases usadas para el transporte de información a través de las capas (todas las clases del paquete com.hackerrank.dto)

DemoApplication: Es la encargada de levantar la aplciación


Frontend:
- jQuery
- HTML5
- Bootstrap (Template Gratuito de Bootstap)

Para el frontend se utilizó un template gratuito de boostrap ofreciéndole a la pagina web responsitive design.

Se utilizó validaciones para los formularios jQuery.validate

Y para la comunicación con el servidor se usó llamadas ajax con el método POST.



CODE REFACTORING
```
```

class Constants {

	const R_ERROR_CODE_STATUS_OK = '0';
	const R_ERROR_CODE_STATUS_UNO = '1';
	const R_ERROR_CODE_STATUS_DOS = '2';
	const R_ERROR_CODE_STATUS_TRES = '3';
	const STATUS_SEIS = '6';
	const STATUS_UNO = '6';
	const UNO = '1';
}

public function post_confirm(){ 

	$id = Input::get('service_id'); 
	$driverId = Input::get('driver_id'); 
	$servicio = Service::find($id); 
	$driverTmp = Driver::find($driverId);

 if($servicio != NULL){
    $servicioStatus = $servicio->status_id;
    if($servicioStatus == Constants::STATUS_SEIS){

        return Response::json(array('error' => Constants::R_ERROR_CODE_STATUS_DOS));
    }
    if($servicio->driver_id == null && $servicioStatus ==  Constants::STATUS_UNO){
        $servicio = Service::update($id, array(
            'driver_id' => $driverId ,
            'status_id' => '2'
        ));

        Driver::update($driverId ,array('available' => 0));

        Service::update($id, array(
            'car_id' => $driverTmp => car_id
        ));

        $pushMessage = 'Tu servicio ha sido confirmado';

        $push = Pusk::make();

        if($servicio->user->uuid == ''){
            return Response::json(array('error' => R_ERROR_CODE_STATUS_DOS));
        }
        if($servicio->user->type == Constants::UNO){//iphone
            $result = $push->ios(XXXXXXXX);
        }else{
            $result = $push->android2(XXXXXXXX);
        }

        return Response::json(array('error' => R_ERROR_CODE_STATUS_OK))
    } else{
        return Response::json(array('error' => R_ERROR_CODE_STATUS_UNO))
    }
} else{
    return Response::json(array('error' => R_ERROR_CODE_STATUS_TRES))
}

```

```
1. Las malas practicas de programación en el código son:
	- Se debe eliminar el código comentado
	- Es mejor si se puede "cachear" variables locales si se van a usar en varias partes en vez de acceder al objeto donde se encuentran

2. Mi refactorización supera lo anterior ya que:
	- Elimino el código comentado
	- "Cacheo" variable $driverId = Input::get('driver_id'); en vez de utilizar varias vecesInput::get('driver_id');





1. La responsabilidad única consiste en implementar nuestros objetos con responsabilidades definidas (alta cohesión) y que la dependencia con otros objetos sea mínima (bajo acoplamiento) como beneficio fundamental nos ayuda a que cuando se requiera realizar un cambio en el código sea lo menos costoso posible

2. Para que un código sea bueno o limpio, en mi opinión debe tener lo siguiente:
	- Bien comentado
	- Bien indentado
	- Los nombres de las clases, miembros y métodos deben ser consistentes con su función
	- Alta cohesión en los objetos
	- Bajo acoplamiento.
	
