<template>
  <div>
      <h1>{{recipe.recipeName}}</h1>
      <p>{{recipe.description}}</p>
      <p>{{recipe.instructions}}</p>
 <ul id="example-1"  v-for="ingredients in recipe.ingredients" v-bind:key="ingredients.ingredientName">
  <li >
    {{ ingredients.ingredientName }}
  </li>
</ul>

  </div>
</template>

<script>

export default {
    name:"recipe-list",
    props: {
        recipe: Object
    }
    }
    

</script>

<style>

</style>