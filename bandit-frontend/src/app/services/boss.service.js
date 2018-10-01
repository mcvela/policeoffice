(function() {
  'use strict';

  angular
    .module('bandit')
    .factory('Boss', Boss);


  /** @ngInject */
  function Boss($resource) {
    return $resource('/api/bosses/:id', {
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
