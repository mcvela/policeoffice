(function() {
  'use strict';

  angular
    .module('bandit')
    .controller('JailController', JailController);

  /** @ngInject */
  function JailController(Jail) {
    var vm = this;

    vm.jails = Jail.query();
    vm.addNew = addNew;
    vm.update = update;
    vm.remove = remove;

    function addNew() {
      var jail = new Jail({
        firstName: '',
        content: '',
        notAvailable: '1',
        idBoss: 0,
        isBoss: 0
      });
      jail.$save(function() {
        //vm.bandits.push(t);

        //silly workaround
        vm.jails = Jail.query();
      });
    }

    function update(jail) {
      Jail.update(jail);
    }

    function remove(jail) {
      jail = new Jail(jail);
      jail.$remove(function() {
        for (var i = 0; i < vm.jails.length; i++) {
          if (vm.jails[i].id == jail.id) {
            vm.jails.splice(i, 1);
          }
        }

      });

    }

  }
})();
