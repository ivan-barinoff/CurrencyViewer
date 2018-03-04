import {SERVER_URL} from '../../config'
import 'whatwg-fetch'

export function fetchRecords() {
    return fetch(SERVER_URL + 'api/fetch').then(handleErrors).then(response => response.json())
}

function handleErrors(response) {
    if (!response.ok) {
        throw Error(response.statusText)
    }
    return response
}