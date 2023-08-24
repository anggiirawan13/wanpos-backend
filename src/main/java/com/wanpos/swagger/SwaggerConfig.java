package com.wanpos.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(

        // API DOC INFORMATION
        info = @Info(

                // APP INFO
                title = "Swagger Documentation Wanpos Project",
                description = "Api Document & Spesification using Swagger-ui by OpenAPI",
                version = "1.0.0",

                // CONTACT
                contact = @Contact(
                        name = "Customer Support",
                        email = "masgong@gmail.com",
                        url = "http://masgong.medium.com"
                ),


                // LICENSE
                license = @License(
                        name = "license name ex (MIT license)",
                        url = "http://masgong.medium.com"

                ),

                // TOS
                termsOfService = "Term of service"
        ),

        // LIST OF SERVER
        servers = {
                @Server(
                        description = "LOCAL ENV",
                        url = "http://localhost:${server.port}"
                ),
                @Server(
                        description = "STAG ENV",
                        url = "${host.staging}:${server.port.staging}"
                )
        },

        // REGISTER @SecurityRequirement to All API (endPoints)
        // name must be same with @SecurityScheme
        // will be added on http header when request api with swagger
        security = {
                @SecurityRequirement(name = "Authorization")
        }
)
// CREATE BUTTON "Authorize" on Swagger Page, for authorize all api
// Value on @SecurityScheme must be same with JWT generator when hit api (/v1/auth/login)
@SecurityScheme(
        name = "Authorization",
        description = "Get Bearer JWT Token from hit api (/user/login)",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {}

