function fn() {
  karate.configure('connectTimeout', 10000);
  karate.configure('readTimeout', 10000);

  var config = {
    baseUrl: 'http://localhost:8080/api'
  };

  return config;
}
