<template>
<div class="main-div">
   <form v-on:submit.prevent class="form-edit">
       <h2>Recipe Name:</h2>
        <input type="text" class="form-control" v-model="newRecipe.recipeName" />
       <h2>Recipe instructions:</h2>
        <textarea
          class="form-control"
          v-model="newRecipe.instructions"
        />
        <h2>Recipe Description:</h2>
        <textarea
          class="form-control"
          v-model="newRecipe.description"/>
          <multiselect v-model="newRecipe.ingredients" :options="ingredients" label="ingredientName" :multiple="true" :close-on-select="false" :clear-on-select="false" :preserve-search="true" placeholder="Select Your Ingredient" track-by="ingredientId" @remove="toggleUnSelectIngredient"></multiselect>
        <div class="button-div">
        <button type="submit" class="btn" v-on:click="updateRecipe()">Update Recipe</button>
        </div>
      </form>
      </div>
</template>

<script>
import recipeService from "@/services/RecipeService";
import ingredientService from "@/services/IngredientService";
import Multiselect from 'vue-multiselect';
export default {
 components:{Multiselect,},
  name: "edit-recipe",
  props: [
      "recipeId"
  ],
  data() {
    return {
      newRecipe: {
        recipeName: "",
        instructions: "",
        description:"",
         ingredients:[]
      },
       ingredients:[]
    };
  },
  methods: {
      
    toggleUnSelectIngredient(ingredientId){
      this.newRecipe.ingredients = this.newRecipe.ingredients.filter((ingredient)=>{
          return ingredient.ingredientId!=ingredientId;
      })

    },
   
    updateRecipe() {
        const recipe ={
        id: this.$route.params.recipeId,
        recipeName: this.newRecipe.recipeName,
        description: this.newRecipe.description,
        instructions: this.newRecipe.instructions,
        ingredients: this.newRecipe.ingredients
        };
      
       recipeService.editRecipe(this.$route.params.recipeId, recipe)
        .then (response=>{
            if(response.status===200){
            this.$router.push({name: 'recipeDetails'});
            }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg =
              "Error adding recipe. Response received was '" +
              error.response.statusText +
              "'.";
          } else if (error.request) {
            this.errorMsg = "Error adding recipe. Server could not be reached.";
          } else {
            this.errorMsg =
              "Error adding card. Request could not be created.";
          }
        });
    }

  },
  computed: {
    options: () => this.ingredients,
  },
  created() {
    recipeService.getRecipeDetails(this.recipeId)
    .then((response) => {
      this.newRecipe.recipeName = response.data.recipeName;
      this.newRecipe.instructions = response.data.instructions;
      this.newRecipe.description = response.data.description;
    }),    
    ingredientService.listIngredients().then((response) => {
      this.ingredients = response.data;
      //this.isLoading = false;
    });
  
  }
};
</script>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style>
.form-control {
  margin-bottom: 10px;
}
.btn {margin-bottom: 35px;}



#add {
  display:flex;
  justify-content: center;

}

.form-edit{
  border: none;
  padding: 10px;
  border-radius: 10px;

}
.main-div{
  width: 30%;
}
.button-div{
  margin: 10px auto 0 auto;
  
}
</style>