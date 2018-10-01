(function() {
  'use strict';

  angular
    .module('bandit')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('home', {
        url: '/',
        templateUrl: 'app/main/home/main.html',
        controller: 'MainController',
        controllerAs: 'main',
        data: {
          roles: ['ROLE_USER']
        }
      })
      .state('bosses', {
        url: '/bosses',
        templateUrl: 'app/main/boss/boss.html',
        controller: 'BossController',
        controllerAs: 'main',
        data: {
          roles: ['ROLE_USER']
        }
      })
      .state('jail', {
        url: '/jail',
        templateUrl: 'app/main/jail/jail.html',
        controller: 'JailController',
        controllerAs: 'main',
        data: {
          roles: ['ROLE_USER']
        }
      })    
      .state('band', {
        url: '/band/:id',
        templateUrl: 'app/main/band/band.html',
        controller: 'BandController',
        controllerAs: 'main',
        data: {
          roles: ['ROLE_USER']
        }
      })   
      .state('login', {
        url: '/login',
        templateUrl: 'app/account/login/login.html',
        controller: 'LoginController',
        controllerAs: 'login'
      })
      .state('profile', {
        url: '/profile',
        templateUrl: 'app/account/profile/profile.html',
        controller: 'ProfileController',
        controllerAs: 'profile',
        data: {
          roles: ['ROLE_USER']
        }
      });

    $urlRouterProvider.otherwise('/');
  }

})();
