

(function() {
  'use strict';

  angular
    .module('bandit')
    .controller('BandController', BandController);

  /** @ngInject */
  function BandController(Band) {
    var vm = this;

    var pathArray = window.location.href.split('/');
    var id=pathArray[pathArray.length - 1];
    var myParam = {};
    myParam['id'] = id; 
    vm.bands = Band.get(myParam);
    vm.addNew = addNew;
    vm.update = update;
    vm.remove = remove;
    vm.test = test;
    
   

    function test(){
       
       
       console.log("test");
    }
    function addNew() {
      var band = new Band({
        firstName: '',
        content: '',
        notAvailable: 0,
        idBoss: 0,
        isBoss: 0
      });
      band.$save(function() {
        //vm.bandits.push(t);

        //silly workaround
       // vm.bands = Band.get(id); // Band.query(id);
      });
    }

    function update(band) {
      Band.update(band);
    }

    function remove(band) {
      band = new Band(band);
      band.$remove(function() {
        for (var i = 0; i < vm.bands.length; i++) {
          if (vm.bands[i].id == band.id) {
            vm.bands.splice(i, 1);
          }
        }

      });

    }

  }
})();



function googleChartBand(jsonData){

  /////////////////////////////////
  var banditsArray=[];
  var jefazo;
  for( var x in jsonData ){
    console.log( jsonData[x] );
    var banditsArrayElements=[];
    var nameBandit=jsonData[x].id+"-"+jsonData[x].firstName;
    var markBoss=(jsonData[x].isBoss==true)? '<div style="color:green; font-style:italic"><a href="#/band/'+jsonData[x].id+'">-His Boss-</a></div>':"";
  
    if (x==0) {
      jefazo=nameBandit;
      markBoss='<div style="color:red; font-style:italic"><a href="#/band/'+jsonData[x].idBoss+'">-His Band-</a></div>';
    }   
     var banditDetail={};
    banditDetail["v"]=nameBandit;
    banditDetail["f"]=nameBandit+markBoss;
    banditsArrayElements[0]=banditDetail;
    
    banditsArrayElements[1]=jefazo;
    banditsArrayElements[2]=jsonData[x].content;
    banditsArray[x]=banditsArrayElements;
  }
  console.log(banditsArray)

  /////////
  if (google){
  
    google.charts.load('current', {packages:["orgchart"]});
    google.charts.setOnLoadCallback(drawChart);
  function drawChart() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Name');
    data.addColumn('string', 'Manager');
    data.addColumn('string', 'ToolTip');
  
    // For each orgchart box, provide the name, manager, and tooltip to show.
    
    /*data.addRows([
     [{v:'Jim', f:'Jim<div style="color:red; font-style:italic">Vice President</div>'},
     banditsList[0], 'VP'],
      [{v:'Mike', f:'Mike<div style="color:red; font-style:italic">President</div>'},
       'Bob', 'The President'],
      ['Alic1e', 'Mike', ''],
      ['Bob', 'Jim', 'Bob Sponge'],
      [banditsList[1], banditsList[0], '']
    ]);*/
    data.addRows(banditsArray);
  
    // Create the chart.
    var chart = new google.visualization.OrgChart(document.getElementById('chart_div'));
    // Draw the chart, setting the allowHtml option to true for the tooltips.
    chart.draw(data, {allowHtml:true});
  }
  
  
 }
}
