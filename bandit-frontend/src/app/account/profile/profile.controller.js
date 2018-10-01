(function() {
  'use strict';

  angular
    .module('bandit')
    .controller('ProfileController', ProfileController);

  /** @ngInject */
  function ProfileController(Auth) {
    var vm = this;

    vm.currentUser = Auth.getCurrentUser();

  }
})();
