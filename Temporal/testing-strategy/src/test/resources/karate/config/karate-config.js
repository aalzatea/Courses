function fn() {
    var port = karate.properties['karate.server.port'] || '8080';
    var protocol = 'http';
    var config = {
        baseUrl: protocol + '://localhost:' + port + '/'
    };

    return config;
}