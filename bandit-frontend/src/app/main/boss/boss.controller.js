(function() {
  'use strict';

  angular
    .module('bandit')
    .controller('BossController', BossController);

  /** @ngInject */
  function BossController(Boss) {
    var vm = this;

    vm.bosses = Boss.query();
    vm.addNew = addNew;
    vm.update = update;
    vm.remove = remove;

    function addNew() {
      var boss = new Boss({
        firstName: '',
        content: '',
        notAvailable: '1',
        idBoss: 1,
        isBoss: 0
      });
      boss.$save(function() {
        //vm.bandits.push(t);

        //silly workaround
        vm.bosses = Boss.query();
      });
    }

    function update(boss) {
      Boss.update(boss);
    }

    function remove(boss) {
      boss = new Boss(boss);
      boss.$remove(function() {
        for (var i = 0; i < vm.bosses.length; i++) {
          if (vm.bosses[i].id == boss.id) {
            vm.bosses.splice(i, 1);
          }
        }

      });

    }

  }
})();
