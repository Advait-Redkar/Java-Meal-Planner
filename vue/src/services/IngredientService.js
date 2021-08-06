import axios from "axios";


export default {
    addIngredient(ingredient){
        return axios.post('/createingredient', ingredient)
    },
    listIngredients(){
        return axios.get('/ingredients')
    }
}