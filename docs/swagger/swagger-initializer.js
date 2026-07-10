window.onload = function() {

    window.ui = SwaggerUIBundle({
        urls: [
            {
                url: "openapi/airbnb-service.json",
                name: "AirBnb"
            }
        ],

        dom_id: "#swagger-ui",
        deepLinking: true,
        presets: [
            SwaggerUIBundle.presets.apis,
            SwaggerUIStandalonePreset
        ],
        plugins: [
            SwaggerUIBundle.plugins.DownloadUrl
        ],
        layout: "StandaloneLayout"
    });
};