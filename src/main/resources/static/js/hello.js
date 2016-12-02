/**
 * Created by Administrator on 2016-11-21.
 */
angular.module('hello', [ 'ngRoute' ])
    .config(function($routeProvider, $httpProvider) {

        $routeProvider.when('/', {
            templateUrl : 'home.html',
            controller : 'home',
            controllerAs: 'controller'
        }).when('/login', {
            templateUrl : 'login.html',
            controller : 'navigation',
            controllerAs: 'controller'
        }).when('/micro', {
            templateUrl : 'micro.html',
            controller : 'micro',
            controllerAs: 'vm'
        }).when('/newuser', {
            templateUrl : 'newuser.html',
            controller : 'addUser',
            controllerAs: 'vm'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    })
    .controller('home', function($http) {
        var self = this;
        $http.get('/hello').then(function(response) {
            self.greeting = response.data;
        });
        $http.get('/getUsername').then(function(response) {
            self.user = response.data;
            console.log('getUsername:');
            console.log(self.user);
        });
        //Kan man få fram den inloggade personens namn här?
    })
    .controller('micro', function($http) {
        var self = this;
        $http.get('resource/').then(function(response) {
            self.greeting = response.data;
        });
    })
    .controller('addUser', function($http, $location) {
        var self = this;
        self.user = {};//objektet mappas mot värden i formuläret.
        self.addUser = function () {
            //console.log('username: ' + self.user.username);
            //console.log('password: ' + self.user.password);
            $http.post('/addUser', self.user).success(function (response) {
                $location.path("/login").search({param: response.response});;
            });
        }//End addUser.
    })
    .controller('navigation',
        function($rootScope, $http, $location) {
            var self = this
            var authenticate = function(credentials, callback) {
                var headers = credentials ? {authorization : "Basic "
                + btoa(credentials.username + ":" + credentials.password)
                } : {};
                $http.get('user', {headers : headers}).then(function(response) {
                    if (response.data.name) {
                        $rootScope.authenticated = true;
                    } else {
                        $rootScope.authenticated = false;
                    }
                    callback && callback();
                }, function() {
                    $rootScope.authenticated = false;
                    callback && callback();
                });
            }

            self.logout = function() {
                $http.post('/logout').finally(function() {
                    $rootScope.authenticated = false;
                    $location.path("/");
                });
            }

            authenticate();
            self.credentials = {};
            self.login = function() {//self.login är ersatt av login formuläret i login.html
                console.log('loggar in ...');
                // authenticate(self.credentials, function() {
                //     if ($rootScope.authenticated) {
                //         $location.path("/");
                //         self.error = false;
                //     } else {
                //         $location.path("/login");
                //         self.error = true;
                //     }
                // });
            };//login

            // console.log($location.search());//object
            // console.log($location.search().param);//OK / ERROR
            self.message = $location.search().param;

        });



