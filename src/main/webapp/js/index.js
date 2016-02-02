var neptuneApp = angular.module('neptuneApp', ['ngResource']);

neptuneApp.value('ownerId', function clientIdFactory() {
  return $("#owner").val();
});

neptuneApp.value('GridLayoutElement', function gridLayoutElement() {
  return $('#eventLayout');
});