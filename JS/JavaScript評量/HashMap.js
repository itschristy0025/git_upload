let HashMap = function () {
    let obj = {};
    return {
        put: function (key, value) {
            obj[key] = value;
        },

        keys: function () {
            return Object.keys(obj);
        },

        contains: function (key) {
            return key in obj;
        },

        get: function (key) {
            return obj[key];
        },

        clear: function () {
            obj = {};
        }

    }
}