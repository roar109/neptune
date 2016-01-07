var neptuneApp = angular.module('neptuneApp', ['ngResource']);

neptuneApp.value('ownerId', function clientIdFactory() {
	//TODO validate if the owner field is not set
  return $("#owner")[0].value;
});

neptuneApp.value('GridLayoutElement', function gridLayoutElement() {
  return $('#eventLayout');
});