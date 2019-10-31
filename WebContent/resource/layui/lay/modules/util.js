/** layui-v2.5.0 MIT License By https://www.layui.com */
;
layui.define("jquery",
function(t) {
    "use strict";
    var e = layui.$,
    i = {
    		/*
     		描述：
     			ajax封装--适用于表单提交，提交过程有刷新提示、结果提示
     		固定规格
    	 		方式			:post
    	 		返回数据		:json
    	 	参数介绍：
    	 		url			:请求地址
    	 		data		:数据
    	 		loadStyle	:加载样式 见layui官网文档
    	 		success		:function	请求成功，返回状态成功
    	 		error		:function	请求成功，返回状态失败
    	 		serverError	:function	请求失败，服务器异常
     		*/
    	formAjax: function(object){
    		object.loadStyle = object.loadStyle || 2;
			//object.method != 'update' || object.method != 'add' || (parent.layer.msg("method参数有误："+object.method), parent.layer.close(index))
			var c = layer.load(object.loadStyle);
			//执行 Ajax 后重载
			e.ajax({
				url: object.url
				,type: 'post'	
				//,contentType: 'application/json'
				,data: object.data
				,dataType: "json"
				,success: function(data){
					data.code == '0' && ('function' == typeof object.success && object.success(data.data, data.msg), layer.close(c), parent.layer.msg(data.msg, object.layCallback)),
					data.code == '2' && ('function' == typeof object.error && object.error(data.data, data.msg), layer.close(c), layer.msg(data.msg));
				} 
				,error: function(data){
					layer.close(c),
					layer.msg("服务器异常，操作失败！"),
					'function' == typeof object.serverError && object.serverError(data, data.msg);
				}
			});
    	} 
    	,layHref: function(){
    		e("body").on('click', "*[lay-href]",
    	    function() {//打开一个标签页
    	    	var t = e(this);
    	    	location.href = t.attr("lay-href");
    	    })
    	}	
        ,fixbar: function(t) {
            var i, n, a = "layui-fixbar",
            o = "layui-fixbar-top",
            r = e(document),
            l = e("body");
            t = e.extend({
                showHeight: 200
            },
            t),
            t.bar1 = t.bar1 === !0 ? "&#xe606;": t.bar1,
            t.bar2 = t.bar2 === !0 ? "&#xe607;": t.bar2,
            t.bgcolor = t.bgcolor ? "background-color:" + t.bgcolor: "";
            var c = [t.bar1, t.bar2, "&#xe604;"],
            g = e(['<ul class="' + a + '">', t.bar1 ? '<li class="layui-icon" lay-type="bar1" style="' + t.bgcolor + '">' + c[0] + "</li>": "", t.bar2 ? '<li class="layui-icon" lay-type="bar2" style="' + t.bgcolor + '">' + c[1] + "</li>": "", '<li class="layui-icon ' + o + '" lay-type="top" style="' + t.bgcolor + '">' + c[2] + "</li>", "</ul>"].join("")),
            s = g.find("." + o),
            u = function() {
                var e = r.scrollTop();
                e >= t.showHeight ? i || (s.show(), i = 1) : i && (s.hide(), i = 0)
            };
            e("." + a)[0] || ("object" == typeof t.css && g.css(t.css), l.append(g), u(), g.find("li").on("click",
            function() {
                var i = e(this),
                n = i.attr("lay-type");
                "top" === n && e("html,body").animate({
                    scrollTop: 0
                },
                200),
                t.click && t.click.call(this, n)
            }), r.on("scroll",
            function() {
                clearTimeout(n),
                n = setTimeout(function() {
                    u()
                },
                100)
            }))
        },
        countdown: function(t, e, i) {
            var n = this,
            a = "function" == typeof e,
            o = new Date(t).getTime(),
            r = new Date(!e || a ? (new Date).getTime() : e).getTime(),
            l = o - r,
            c = [Math.floor(l / 864e5), Math.floor(l / 36e5) % 24, Math.floor(l / 6e4) % 60, Math.floor(l / 1e3) % 60];
            a && (i = e);
            var g = setTimeout(function() {
                n.countdown(t, r + 1e3, i)
            },
            1e3);
            return i && i(l > 0 ? c: [0, 0, 0, 0], e, g),
            l <= 0 && clearTimeout(g),
            g
        },
        timeAgo: function(t, e) {
            var i = this,
            n = [[], []],
            a = (new Date).getTime() - new Date(t).getTime();
            return a > 6912e5 ? (a = new Date(t), n[0][0] = i.digit(a.getFullYear(), 4), n[0][1] = i.digit(a.getMonth() + 1), n[0][2] = i.digit(a.getDate()), e || (n[1][0] = i.digit(a.getHours()), n[1][1] = i.digit(a.getMinutes()), n[1][2] = i.digit(a.getSeconds())), n[0].join("-") + " " + n[1].join(":")) : a >= 864e5 ? (a / 1e3 / 60 / 60 / 24 | 0) + "天前": a >= 36e5 ? (a / 1e3 / 60 / 60 | 0) + "小时前": a >= 12e4 ? (a / 1e3 / 60 | 0) + "分钟前": a < 0 ? "未来": "刚刚"
        },
        digit: function(t, e) {
            var i = "";
            t = String(t),
            e = e || 2;
            for (var n = t.length; n < e; n++) i += "0";
            return t < Math.pow(10, e) ? i + (0 | t) : t
        },
        toDateString: function(t, e) {
            var i = this,
            n = new Date(t || new Date),
            a = [i.digit(n.getFullYear(), 4), i.digit(n.getMonth() + 1), i.digit(n.getDate())],
            o = [i.digit(n.getHours()), i.digit(n.getMinutes()), i.digit(n.getSeconds())];
            return e = e || "yyyy-MM-dd HH:mm:ss",
            e.replace(/yyyy/g, a[0]).replace(/MM/g, a[1]).replace(/dd/g, a[2]).replace(/HH/g, o[0]).replace(/mm/g, o[1]).replace(/ss/g, o[2])
        },
        escape: function(t) {
            return String(t || "").replace(/&(?!#?[a-zA-Z0-9]+;)/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/'/g, "&#39;").replace(/"/g, "&quot;")
        },
        event: function(t, n, a) {
            n = i.event[t] = e.extend(!0, i.event[t], n) || {},
            e("body").on(a || "click", "*[" + t + "]",
            function() {
                var i = e(this),
                a = i.attr(t);
                n[a] && n[a].call(this, i)
            })
        }
    }; !
    function(t, e, i) {
        "$:nomunge";
        function n() {
            a = e[l](function() {
                o.each(function() {
                    var e = t(this),
                    i = e.width(),
                    n = e.height(),
                    a = t.data(this, g); (i !== a.w || n !== a.h) && e.trigger(c, [a.w = i, a.h = n])
                }),
                n()
            },
            r[s])
        }
        var a, o = t([]),
        r = t.resize = t.extend(t.resize, {}),
        l = "setTimeout",
        c = "resize",
        g = c + "-special-event",
        s = "delay",
        u = "throttleWindow";
        r[s] = 250,
        r[u] = !0,
        t.event.special[c] = {
            setup: function() {
                if (!r[u] && this[l]) return ! 1;
                var e = t(this);
                o = o.add(e),
                t.data(this, g, {
                    w: e.width(),
                    h: e.height()
                }),
                1 === o.length && n()
            },
            teardown: function() {
                if (!r[u] && this[l]) return ! 1;
                var e = t(this);
                o = o.not(e),
                e.removeData(g),
                o.length || clearTimeout(a)
            },
            add: function(e) {
                function n(e, n, o) {
                    var r = t(this),
                    l = t.data(this, g) || {};
                    l.w = n !== i ? n: r.width(),
                    l.h = o !== i ? o: r.height(),
                    a.apply(this, arguments)
                }
                if (!r[u] && this[l]) return ! 1;
                var a;
                return t.isFunction(e) ? (a = e, n) : (a = e.handler, void(e.handler = n))
            }
        }
    } (e, window),
    t("util", i)
});