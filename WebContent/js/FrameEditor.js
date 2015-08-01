/**
 * 
 */
(function() {
  var jqEvent = function() {
    $("#content-select").on("click", "a", function() {
      $(".pure-menu-selected").removeClass("pure-menu-selected");
      $(this).parent().addClass("pure-menu-selected");
    })
  }
  var ngEvent = function() {
    var viewPattern = "";
    angular.module("editorFrame", ["ngRoute"]).config(
            ["$routeProvider", "$locationProvider", "contentFactory",
                function(route, loc, content) {
                  loc.html5Mode(false).hashPrefix("!");
                  route.when("/preview", {
                    templateUrl: "editorPieces/preview.html",
                    controller: "preview",
                    resolve: {}
                  }).when("/members", {
                    templateUrl: "editorPieces/members.html",
                    controller: "members",
                    resolve: {}
                  }).when("/details", {
                    templateUrl: "editorPieces/details.html",
                    controller: "details.html",
                    resolve: {}
                  }).when("/teacher", {
                    templateUrl: "editorPieces/teacherOrTutor.html",
                    controller: "teacher",
                    resolve: {}
                  }).when("/tutor", {
                    templateUrl: "editorPieces/teacherOrTutor.html",
                    controller: "tutor",
                    resolve: {}
                  }).when("/", {
                    templateUrl: "editorPieces/teacherOrTutor.html",
                    controller: "tutor",
                    resolve: {}
                  }).otherwise({
                    templateUrl: function(path) {
                      var p = path.split("\/");
                      viewPattern = p[0];
                      return "editorPieces/wmd-editor.html";
                    },
                    controller: "wmdEditor",
                    resolve: {
                      "content": function() {

                        return content[viewPattern];

                      }
                    }
                  })
                  // FIXME: get content loaded from preview since the preview
                  // view is usually the first view to be loaded.
                  // FIXME: what if user access the page with a url that is not
                  // default one?we should check content is loaded or not
                  // so the resolve should get a promise which will return a
                  // text object
                }]).factory(
            "contentFactory",
            ["$q", "$http", "$location", "errorHDLSrv",
                function($q, $http, loc, errorHandle) {
                  var content{};
                  var pid = loc.search('pid');
                    $http.get("content", {
                      pid: pid
                    }).success(function(data, status) {
                      if (status == 200) {
                        // the normal response should be a JSON object
                        content = angular.fromJson(data);
                        content.isInitialed = true;
                        return content;
                      } else {
                        errorHandle.contentLoadError(data, status);
                      }
                    }).error(function(data, status) {
                      errorHandle.contentLoadError(data, status);
                    })
                    return content;
                  }]).service("errorHDLSrv", [function() {
      return {
        contentLoadError: function(data, status) {
          // FIXME: display the error message at the error band.
        }
      }
    }])
  }
  $(document).ready(function() {
    jqEvent();
  })
})();