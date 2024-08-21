let HashMap = function () {
    let obj = {};
    return {
        put: function (key, value) {
            obj[key] = value;
        },

        keys: function () {
            let ary = [];
            for (const obkey in obj) {
                ary.push(obkey);
            }
            return ary;
        },

        contains: function (key) {
            for (const obkey in obj) {
                if (obkey === key) {
                    return true;
                }
            }
            return false;
        },

        get: function (key) {
            return obj[key];
        },

        clear: function () {
            obj = {};
        }




    }
}