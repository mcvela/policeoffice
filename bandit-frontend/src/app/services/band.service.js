(function() {
  'use strict';

  angular
    .module('bandit')
    .factory('Band', Band);


  /** @ngInject */
  function Band($resource) {
    return $resource('/api/band/:id', {
      id: '@id'
    }, {
      'get': {
        method: 'GET',
        params: {id: '@id'},
        isArray: true,
        interceptor: {
          response: function(response) {
            // expose response
            // TODO: implement roles in backend!
            // alert("1 interceptor");
            return response;
          }
        },
        transformResponse: function(response) {
          
          var jsonData = angular.fromJson(response);
          //jsonData.roles = ['ROLE_USER'];
          console.log(jsonData);
          if (google)   googleChartBand(jsonData); 
          return jsonData;
        }
      },
      query: {
        method: 'get',
        params: {id: '@id'},
        isArray: true,
        cancellable: true
      },
      update: {
        method: 'PUT'
      }
    });
  }
    /** @ngInject */
    /*
  function Account($resource) {
    return $resource('api/account', {}, {
      'get': {
        method: 'GET',
        params: {},
        isArray: false,
        interceptor: {
          response: function(response) {
            // expose response
            // TODO: implement roles in backend!
            return response;
          }
        },
        transformResponse: function(response) {
          var jsonData = angular.fromJson(response);
          jsonData.roles = ['ROLE_USER'];
          return jsonData;
        }
      }
    });
  }
  */
})();
