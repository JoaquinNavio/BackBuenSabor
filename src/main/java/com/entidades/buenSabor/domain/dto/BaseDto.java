package com.entidades.buenSabor.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*Los DTO (Data Transfer Object)
son objetos utilizados para transferir datos entre diferentes capas de una aplicación. */

/*¿Para qué sirven los DTO?
* Encapsulamiento de Datos:
Los DTO encapsulan los datos que se transferirán entre la capa de presentación (controladores)
y la capa de servicio o lógica de negocio (facades, servicios).
Esto garantiza que sólo los datos necesarios se transfieran, mejorando la eficiencia y la seguridad.

*Separación de Preocupaciones:
Separan la lógica de negocio de la representación de los datos.
Las entidades de negocio pueden contener lógica adicional
o estar relacionadas de formas que no son relevantes para la capa de presentación.

* Simplificación de Objetos:
Los DTO suelen ser más simples y específicos en comparación con las entidades de negocio.
Esto simplifica la transferencia de datos y evita el envío de información innecesaria o sensible.

* Prevención de Problemas de Serialización:
Al usar DTO, se evita la serialización de objetos complejos que pueden causar problemas de rendimiento o seguridad.*/


/*¿Por qué dividir en DTO y CreateDTO?
Propósitos Diferentes:
* DTO:
Representa la forma de los datos cuando se envían o reciben del cliente.
Contiene toda la información que necesita ser mostrada o enviada.
* CreateDTO: Se usa específicamente para la creación de nuevos registros.
Contiene sólo los datos necesarios para crear una nueva entidad,
excluyendo atributos generados o derivados como IDs.*/

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseDto {
    private Long id;
}
