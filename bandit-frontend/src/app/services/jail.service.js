(function() {
  'use strict';

  angular
    .module('bandit')
    .factory('Jail', Jail);


  /** @ngInject */
  function Jail($resource) {
    return $resource('/api/jails/:id', {
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
