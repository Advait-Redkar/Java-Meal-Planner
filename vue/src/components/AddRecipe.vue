<template>
  <div>
    <h1>Add Recipe Page</h1>
    <div>
      <form v-on:submit.prevent>
        Recipe Name:
        <input type="text" class="form-control" v-model="newRecipe.recipeName" />
        Recipe Description:
        <input
          type="text"
          class="form-control"
          v-model="newRecipe.instructions"
        />
        <button type="submit" class="btn" v-on:click="createRecipe">Add Recipe</button>
      </form>
    </div>
  </div>
</template>

<script>
import recipeService from "@/services/RecipeService";
export default {
  name: "add-recipe",
  data() {
    return {
      newRecipe: {
        recipeName: "",
        instructions: "",
      },
    };
  },
  methods: {
    createRecipe() {
      recipeService
        .addRecipe(this.newRecipe)
        .then((response) => {
          if (response.status === 201) {
            alert("Recipe Succesfully Added");
            this.$router.push("/home");
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
    },
  },
};
</script>

<style>
.form-control {
  margin-bottom: 10px;
}
.btn {margin-bottom: 35px;}
</style>
