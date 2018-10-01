(function() {
  'use strict';

  angular
    .module('bandit')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController(Bandit) {
    var vm = this;
    vm.bandits = Bandit.query();
    vm.addNew = addNew;
    vm.update = update;
    vm.remove = remove;

    function addNew() {
      var bandit = new Bandit({
        firstName: '',
        content: '',
        notAvailable: 0,
        idBoss: 0,
        isBoss: 0
      });
      bandit.$save(function() {
        //vm.bandits.push(t);

        //silly workaround
        vm.bandits = Bandit.query();
      });
    }

    function update(bandit) {
      Bandit.update(bandit);
    }

    function remove(bandit) {
      bandit = new Bandit(bandit);
      bandit.$remove(function() {
        for (var i = 0; i < vm.bandits.length; i++) {
          if (vm.bandits[i].id == bandit.id) {
            vm.bandits.splice(i, 1);
          }
        }

      });

    }

  }
})();
