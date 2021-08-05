<template>
  <div>
      <recipe-details v-bind:recipe="recipe"></recipe-details>
  </div>
</template>

<script>
import RecipeDetails from '../components/RecipeDetails'
import RecipeService from '../services/RecipeService'

export default {
  components: {
       RecipeDetails 
       },
    created() {
           // let recipe = {}
            RecipeService.getRecipeDetails(this.$route.params.recipeId)
            .then (response => {
                this.recipe = response.data;
            })
           // return recipe;

    },

    data(){
        return{
            recipe:{}
        }
    }

}
</script>

<style>

</style>