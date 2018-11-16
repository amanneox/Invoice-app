/* eslint-disable */
import axios from 'axios'
const config = {
  apiUrl:'http://paperless-dev.ap-south-1.elasticbeanstalk.com'
}
export const userinvoiceService = {
  get_All_userinvoice,
  getById,
  update,
  _delete,
  create
}
async function get_All_userinvoice () {
  const requestOptions = {
    headers: {
      'Content-Type': 'application/json',
      "Access-Control-Allow-Origin" : "*",
      "Access-Control-Allow-Credentials" : true
   },
  }
try {
  const res = await axios.get(`${config.apiUrl}/user/invoice/`)
  return res.data
} catch (error) {
    return Promise.reject(error)
}
  }

async function getById (id) {
  const requestOptions = {
    headers: {
      'Content-Type': 'application/json',
      "Access-Control-Allow-Origin" : "*",
      "Access-Control-Allow-Credentials" : true
   },
  }
try {
  const res = await axios.get(`${config.apiUrl}/invoice/${id}`)

  return res.data
} catch (error) {
    return Promise.reject(error)
}
  }

async function update (invoice) {

  const requestOptions = {
    headers: {
      'Content-Type': 'application/json',
      "Access-Control-Allow-Origin" : "*",
      "Access-Control-Allow-Credentials" : true
   },
    ...invoice
  }
try {
  const res = await axios.post(`${config.apiUrl}/user/invoice/update/${invoice.id}`, requestOptions)

} catch (error) {
  return Promise.reject(error)
}
}

async function create (invoice) {
  const requestOptions = {
    headers: {
      'Content-Type': 'application/json',
      "Access-Control-Allow-Origin" : "*",
      "Access-Control-Allow-Credentials" : true
   },
    ...invoice
  }
console.log(requestOptions)
try {
  const res = await axios.post(`${config.apiUrl}/user/invoice/`, requestOptions)

} catch (error) {
  return Promise.reject(error)
 }
}
async function _delete (id) {
  const requestOptions = {
    headers: {
      'Content-Type': 'application/json',
      "Access-Control-Allow-Origin" : "*",
      "Access-Control-Allow-Credentials" : true
   },
  }
try {
  const res = await axios.post(`${config.apiUrl}/user/invoice/delete/${id}`, requestOptions)
} catch (error) {
  return Promise.reject(error)
}

}
