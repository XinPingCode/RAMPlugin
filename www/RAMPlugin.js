var exec = require('cordova/exec');

exports.coolMethod = function(arg0, success, error) {
    exec(success, error, "RAMPlugin", "coolMethod", [arg0]);
};
exports.getRAM = function(arg0, success, error) {
    exec(success, error, "RAMPlugin", "getRAM", [arg0]);
};
