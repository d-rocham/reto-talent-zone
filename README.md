# reto-talent-zone

![Esquema de la aplicación](https://user-images.githubusercontent.com/65421290/214378851-ec2add6e-18a6-4cff-a117-78b21fb490ed.jpg)

## Características
* Aplicación compuesta por dos microservicios (omega y delta), reactiva y diseñada en capas (api, business, domain, persistence).
* La aplicación aprovecha los patrones de diseño **DTO** *(Data Transfer Object)* y **BO** *(Business Object)* para encapsular de manera adecuada la información. Los DTOs carecen de lógica de negocio y son utilizados exclusivamente para exponer la información al cliente, mientras que los BOs incorporan lógica de negocio para validar reglas de negocio con respecto a la disponibilidad de los productos. De esta forma, los modelos almacenados en la base de datos se utilizan únicamente en la capa de Persistence
* El microservicio *omega* expone la base de datos al cliente por medio de un servicio diseñado en capas, mientras que el microservicio *delta* abre un WebSocket, mediante el cual se notifican las nuevas ventas. Dichos mensajes son recibidos por medio de un queue de RabbitMQ a través de CloudAMQP.

## Puntos de mejora
* Pese a que la aplicación funciona a través de Postman, los tests no terminan con un resultado correcto debido a bugs en los mismos tests. Debo repasar testing.
* A nivel de buenas prácticas para aplicaciones empresariales, actualmente la aplicación emplea excepciones genéricas. Lo ideal es crear Excepciones personalizadas que reflejen los errores propios de la lógica de negocio (por ejemplo, en vez de retornar un simple *500 SERVER ERROR* se debería retornar una respuesta que explique por qué la solicitud no pudo ser completada. Esto ocurre en el caso de uso para hacer una compra).
