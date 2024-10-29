// import axios from "axios";
// import {User} from "../../types/types.ts"
//
// export async function getUsers() : Promise<User[]> {
//     const response = await axios.get(
//         `${import.meta.env.VITE_API_URL}/users`, {responseType: "json"});
//     return response.data;
// }
//
// export async function deleteUser(user: User) : Promise<void> {
//     await axios.delete(`${import.meta.env.VITE_API_URL}/users/${user.id}`, {responseType: "json"});
// }
//
// export async function createUser(user: User) : Promise<User> {
//     console.log(user);
//
//     const response = await axios.post(
//         `${import.meta.env.VITE_API_URL}/users`, user, {
//             headers: { contentType: "application/json"},
//             responseType: "json"
//         });
//
//     return response.data;
// }
