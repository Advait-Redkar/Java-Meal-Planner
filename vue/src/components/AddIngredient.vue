<template>
<div id = "add">
    <div>
      <form v-on:submit.prevent>
        Ingredient Name:
        <input type="text" class="form-control" v-model="newIngredient.ingredientName" />
        <button type="submit" class="btn" v-on:click="createIngredient">Add Ingredient</button>
      </form>
    </div>
  </div>
  
</template>

<script>
import ingredientService from "@/services/IngredientService";


export default {
name: "add-ingredient",
  data() {
    return {
      newIngredient: {
        ingredientName: "",
      },
    };
  },
   methods: {
    createIngredient() {
      ingredientService
        .addIngredient(this.newIngredient)
        .then((response) => {
          if (response.status === 200) {
            alert("Ingredient Succesfully Added");
            this.$router.push({ name: 'home' });
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg =
              "Error adding ingredient. Response received was '" +
              error.response.statusText +
              "'.";
          } else if (error.request) {
            this.errorMsg = "Error adding ingredient. Server could not be reached.";
          } else {
            this.errorMsg =
              "Error adding card. Request could not be created.";
          }
        });
    },
  },
}
</script>

<style>

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