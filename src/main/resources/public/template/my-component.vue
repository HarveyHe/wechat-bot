<template>
    <div class="hello">Hello {{who}}{{ha}}</div>
</template>

<script>
module.exports = {
    data: function() {
        return {
            who: 'Test',
            ha: "w2"
        }
    }
}
</script>

<style>
.hello {
    background-color: #ffe;
}
</style>