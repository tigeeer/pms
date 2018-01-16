let Tools;

(function (toast, loading) {
    init();
    Tools = {
        toast: toast(),
        loading: loading()
    };
}(function() {
    return {
        container: null,
        createContainer: function () {
            let container = document.createElement('div');
            container.setAttribute('class', 'toast-container');
            this.container = container;
            document.getElementsByTagName('body')[0].appendChild(container);
        },
        getContainer: function () {
            if (this.container === null) {
                this.createContainer();
            }

            return this.container;
        },
        success: function (text) {
            this.createToast(text, 'toast toast-success', 4000);
        },
        error: function (text) {
            this.createToast(text, 'toast toast-error', 4000 )
        },
        createToast: function (text, nodeClass, duration) {
            let toastNode = document.createElement('div');
            let textNode = document.createTextNode(text);
            toastNode.appendChild(textNode);
            toastNode.setAttribute('class', nodeClass);
            let container = this.getContainer();
            container.appendChild(toastNode);
            setTimeout(function() {
                container.removeChild(toastNode);
            }, duration);
        }
    };
}, function () {
    return {
        container: null,
        backdrop: true,
        show: function () {
            let container = document.createElement('div');
            container.setAttribute('class', 'loading-container');
            let loading = document.createElement('div');
            loading.setAttribute('class', 'loading');
            let icon = document.createElement('i');
            icon.setAttribute('class', 'fa fa-circle-o-notch fa-spin fa-3x fa-fw');
            loading.appendChild(icon);
            container.appendChild(loading);
            this.container = container;
            let body = document.getElementsByTagName('body')[0];
            body.appendChild(container);
            loading.addEventListener('click', function (e) {
                e.stopPropagation()
            });
            container.addEventListener('click', function () {
                if (this.backdrop) {
                    this.remove();
                }
            }.bind(this));
        },
        remove: function () {
            if (this.container) {
                this.container.remove();
                this.container = null;
            }
        }
    }
}));

function init() {
    console.log('init');
}