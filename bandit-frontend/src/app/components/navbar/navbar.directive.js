(function() {
  'use strict';

  angular
    .module('bandit')
    .directive('banditNavbar', banditNavbar);

  /** @ngInject */
  function banditNavbar() {
    var directive = {
      restrict: 'E',
      templateUrl: 'app/components/navbar/navbar.html',
      scope: {
          // creationDate: '='
      },
      controller: NavbarController,
      controllerAs: 'vm',
      bindToController: true
    };

    return directive;

  }

  /** @ngInject */
  function NavbarController(Auth, $state) {
    var vm = this;

    vm.navCollapsed = true;
    vm.user = Auth.getCurrentUser();
    vm.logout = logout;

    function logout() {
      Auth.logout();
      $state.go('login');
    }
  }

})();
