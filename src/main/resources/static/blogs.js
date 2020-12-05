var blogs = [];

var timestamp = Date.now();

function findBlog(blogId) {
    return blogs[findBlogKey(blogId)];
}


function findBlogKey(blogId) {
    for (var key = 0; key < blogs.length; key++) {
        if (blogs[key].id == blogId) {
            return key;
        }
    }
}

var blogService = {
    findAll(fn) {
        axios
            .get('/api/v1/blogs')
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    findById(id, fn) {
        axios
            .get('/api/v1/blogs/' + id)
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    create(blog, fn) {
        axios
            .post('/api/v1/blogs', blog)
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    update(id, blog, fn) {
        axios
            .put('/api/v1/blogs/' + id, blog)
            .then(response => fn(response))
            .catch(error => console.log(error))
    },

    deleteBlog(id, fn) {
        axios
            .delete('/api/v1/blogs/' + id)
            .then(response => fn(response))
            .catch(error => console.log(error))
    }
};

var List = Vue.extend({
    template: '#blog-list',
    data: function () {
        return {blogs: [], searchKey: ''};
    },
    computed: {
        filteredBlogs() {
            return this.blogs.filter((blog) => {
                return blog.name.indexOf(this.searchKey) > -1
                    || blog.description.indexOf(this.searchKey) > -1
                    || blog.price.toString().indexOf(this.searchKey) > -1
            })
        }
    },
    mounted() {
        blogService.findAll(r => {
            this.blogs = r.data;
            blogs = r.data
        })
    }
});

var Blog = Vue.extend({
    template: '#blog',
    data: function () {
        return {blog: findBlog(this.$route.params.blog_id)};
    }
});


var BlogEdit = Vue.extend({
    template: '#blog-edit',
    data: function () {
        return {blog: findBlog(this.$route.params.blog_id)};
    },
    methods: {
        updateBlog: function () {
            blogService.update(this.blog.id, this.blog, r => router.push('/'))
        }
    }
});

var BlogDelete = Vue.extend({
    template: '#blog-delete',
    data: function () {
        return {blog: findBlog(this.$route.params.blog_id)};
    },
    methods: {
        deleteBlog: function () {
            blogService.deleteBlog(this.blog.id, r => router.push('/'))
        }
    }
});

var AddBlog = Vue.extend({

    template: '#add-blog',
    data() {
        return {
            blog: {name: '', description: '', date: timestamp}
        }
    },
    methods: {
        createBlog() {
            blogService.create(this.blog, r => router.push('/'))
        }
    }
});

var router = new VueRouter({
    routes: [
        {path: '/', component: List},
        {path: '/blog/:blog_id', component: Blog, name: 'blog'},
        {path: '/add-blog', component: AddBlog},
        {path: '/blog/:blog_id/edit', component: BlogEdit, name: 'blog-edit'},
        {path: '/blog/:blog_id/delete', component: BlogDelete, name: 'blog-delete'}
    ]
});

new Vue({
    router
}).$mount('#app');