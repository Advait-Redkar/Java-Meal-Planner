<template>
  <div id = "add">
    <div>
      <h1>Add Recipe Page</h1>
      <form v-on:submit.prevent>
        Recipe Name:
        <input type="text" class="form-control" v-model="newRecipe.recipeName" />
        Recipe instructions:
        <textarea
          class="form-control"
          v-model="newRecipe.instructions"
        />
          Recipe Description:
        <textarea
          class="form-control"
          v-model="newRecipe.description"/>
<!--    selector v-for on the option
        in data array of ingredients
        crreated section set array ingredients to result of calling backened 
  <div class="dropdown">
        <button class="dropbtn">Dropdown</button>
        <div class="dropdown-content">
        <a href="#">Link 1</a>
        <a href="#">Link 2</a>
        <a href="#">Link 3</a>
        </div>
        </div>-->
        <button class="dropbtn" >Dropdown</button>
        <button type="submit" class="btn" v-on:click="createRecipe">Add Recipe</button>

      </form>
    </div>
  </div>
</template>

<script>
import recipeService from "@/services/RecipeService";
import ingredientService from "@/services/IngredientService";
export default {
  name: "add-recipe",
  data() {
    return {
      newRecipe: {
        recipeName: "",
        instructions: "",
        description:"",
        ingredients:{}
      },
      ingredients:[]
    };
  },
  methods: {
    createRecipe() {
      recipeService
        .addRecipe(this.newRecipe)
        .then((response) => {
          if (response.status === 200) {
            alert("Recipe Succesfully Added");
            this.$router.push({ name: 'home' });
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
  computed: {
    options: () => this.ingredients,
  },
  created() {
    ingredientService.listIngredients().then(response => {
      this.ingredients = response.data;
      //this.isLoading = false;
    });
  }
};
</script>

<style>
.form-control {
  margin-bottom: 10px;
}
.btn {margin-bottom: 35px;}

form {
  display: flex;
  flex-direction: column;
  margin: 10px;
}

#add {
  display:flex;
  justify-content: center;

}

form{
  border: black 3px solid;
  padding: 10px;
  border-radius: 10px;

}
</style>
