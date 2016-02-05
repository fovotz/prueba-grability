$(document).ready(function(){

  $.extend( $.validator.messages, {
      required: "Este campo es obligatorio.",
      remote: "Por favor, rellena este campo.",
      email: "Por favor, escribe una dirección de correo válida.",
      url: "Por favor, escribe una URL válida.",
      date: "Por favor, escribe una fecha válida.",
      dateISO: "Por favor, escribe una fecha (ISO) válida.",
      number: "Por favor, escribe un número válido.",
      digits: "Por favor, escribe sólo dígitos.",
      creditcard: "Por favor, escribe un número de tarjeta válido.",
      equalTo: "Por favor, escribe el mismo valor de nuevo.",
      extension: "Por favor, escribe un valor con una extensión aceptada.",
      maxlength: $.validator.format( "Por favor, no escribas más de {0} caracteres." ),
      minlength: $.validator.format( "Por favor, no escribas menos de {0} caracteres." ),
      rangelength: $.validator.format( "Por favor, escribe un valor entre {0} y {1} caracteres." ),
      range: $.validator.format( "Por favor, escribe un valor entre {0} y {1}." ),
      max: $.validator.format( "Por favor, escribe un valor menor o igual a {0}." ),
      min: $.validator.format( "Por favor, escribe un valor mayor o igual a {0}." ),
      nifES: "Por favor, escribe un NIF válido.",
      nieES: "Por favor, escribe un NIE válido.",
      cifES: "Por favor, escribe un CIF válido."
    } );

  
  var elements = {
      operaciones : $("#Operaciones"),
      datosBox : $("#DatosBox"),
      operacionesBox : $("#OperacionesBox"),
      operacionSelect : $("#OperacionSelect"),
      divQuery : $("#DivQuery"),
      divUpdate : $("#DivUpdate"),
      consola : $("#Consola"),
      tituloOperaciones : $("#Titulo-Operaciones"),
      labelCantidadOperaciones : $("#LabelCantidadOperaciones")
      
       
    };
  
  var globals = {
	matrixId : 0,
	cantidadAcciones : 0,
	accionActual : 0,
	cantidadCasos : 0,
	casoActual : 0,
	
  };
  $("#Operaciones").validate({
    submitHandler: function(form) {
      var selectVal = form.OperacionSelect.value,
          json = {};
      
      
      if(globals.accionActual < globals.cantidadAcciones){
    	  json.operacion = selectVal;
          json.matrixId = globals.matrixId;
          
          if("QUERY" == selectVal){
            
            json.x1 = form.qx1.value;
            json.y1 = form.qy1.value;
            json.z1 = form.qz1.value;
            json.x2 = form.qx2.value;
            json.y2 = form.qy2.value;
            json.z2 = form.qz2.value;
            

            
          }else{

            json.x1 = form.x1.value;
            json.y1 = form.y1.value;
            json.z1 = form.z1.value;
            json.w  = form.W.value;
            
          }

          $.ajax({
              type: 'POST',
              url: '/prueba/accion',
              data: JSON.stringify(json),
              success: function(data) { 
              	if(!data.error){
              		limpiarAcciones();
              		elements.labelCantidadOperaciones.html("Cantidad de Operación " + (globals.accionActual + 1));
              		if("QUERY" == selectVal){
              			elements.consola.val(elements.consola.val() + '\n' +data.suma);
              		}else{
              			elements.consola.val(elements.consola.val() + '\nOK');
              		}
              		globals.accionActual++;
              	}else{
              		alert(data.msgError);
              	}
              },
              contentType: "application/json",
              dataType: 'json'
          });
      }else{
    	  elements.consola.val(elements.consola.val() + '\n---FIN---');
    	  alert("Ya se cumplio el máximo de operaciones");
    	  console.log("globals ",globals);
    	  if(globals.casoActual < globals.cantidadCasos){
    		  elements.operacionesBox.hide();
    		  
              elements.datosBox.show();
              elements.datosBox.find("#casos").val(globals.cantidadCasos);
              elements.datosBox.find("#casos").attr('disabled','disabled');
    	  }else{
    		  alert("Ya se cumplieron los casos");
    		  window.location.reload();
    	  }
      }
      
      

      return false;
    }
  });


  $("#Formulario").validate({
        rules: {
          casos: {
              required: true,
              number: true
            },
            N: {
                required: true,
                number: true
              },
              M: {
                  required: true,
                  number: true
                },
          },
          submitHandler: function(form) {
            var json = {
              casos : form.casos.value,
              m : form.M.value,
              n : form.N.value
            };

            try{
         
              
          $.ajax({
                type: 'POST',
                url: '/prueba/enviar',
                data: JSON.stringify(json),
                success: function(data) { 
                	elements.operacionesBox.show();
                    elements.datosBox.hide();
                    
                    
                    globals.matrixId = data.matrixId;
                    globals.cantidadAcciones = data.m;
                    globals.n = data.n;
                    globals.accionActual = 0;
                    
                    globals.cantidadCasos = data.casos;
                    globals.casoActual++;
                    
                },
                contentType: "application/json",
                dataType: 'json'
            });
            
            return false;
            }catch(e){
              console.log("Error " , e);
          }
          


            
          }
        });


  

  elements.operacionSelect.off("change");
  elements.operacionSelect.on("change",function(){
    
    var val = $(this).val();
    limpiarAcciones();
    if("QUERY" == val){
      elements.divQuery.show();
      elements.divUpdate.hide();
    }else{
      elements.divQuery.hide();
      elements.divUpdate.show();
    }
    
  });

  function limpiarAcciones(){
	  	elements.divQuery.find("input").val("");
	    elements.divUpdate.find("input").val("");  
  }


  elements.operacionesBox.hide();
  elements.divQuery.hide();
  elements.divUpdate.hide();
});  