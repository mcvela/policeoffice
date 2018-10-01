(function() {
  'use strict';

  angular
    .module('bandit')
    .factory('Bandit', Bandit);


  /** @ngInject */
  function Bandit($resource) {
    return $resource('/api/bandits/:id', {
      id: '@id'
    }, {
      query: {
        method: 'get',
        isArray: true,
        cancellable: true
      },
      update: {
        method: 'PUT'
      }
    });
  }
})();
