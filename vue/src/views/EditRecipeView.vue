<template>
  <div>
      <edit-recipe v-bind:recipeId="parseInt($route.params.recipeId)">
      </edit-recipe>
  </div>
</template>

<script>
import EditRecipe from '@/components/EditRecipe'
export default {
components:{
    EditRecipe
}
}
</script>

<style>

</style>