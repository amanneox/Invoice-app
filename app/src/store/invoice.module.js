import { invoiceService } from '../services'

const state = {
  items: {},
  request: {},
  current: {}
}

const actions = {
  get_All ({ commit }) {
    commit('get_AllRequest')

    invoiceService.get_All()
      .then(
        invoice => {
          commit('get_AllSuccess', invoice)
        },
        error => commit('get_AllFailure', error)
      )
  },
  getById ({commit}, id) {
    invoiceService.getById(id)
      .then(
        current => {
          commit('getByIdSuccess', current)
        },
        error => commit('get_AllFailure', error)
      )
  },
  create ({commit}, payload) {
    commit('createRequest')
    invoiceService.create(payload)
      .then(
        commit('createSuccess', 'success'),
        error => commit('createFailure', error)
      )
  },
  update ({commit}, payload) {
    invoiceService.update(payload)
      .then(
        commit('createSuccess', 'success'),
        error => commit('createFailure', error)
      )
  },
  delete ({ commit }, id) {
    commit('deleteRequest', id)

    invoiceService.delete(id)
      .then(
        invoice => commit('deleteSuccess', id),
        error => commit('deleteSuccess', { id, error: error.toString() })
      )
  }
}

const mutations = {
  createRequest (state) {
    state.request = { loading: true }
  },
  createSuccess (state, msg) {
    state.request = { request: msg }
  },
  createFailure (state, error) {
    state.request = { error }
  },
  get_AllRequest (state) {
    state.request = { loading: true }
  },
  get_AllSuccess (state, invoice) {
    state.items = { ...invoice }
  },
  getByIdSuccess (state, data) {
    state.current = {...data}
  },
  get_AllFailure (state, error) {
    state.request = { error }
  },
  deleteRequest (state, id) {
    // add 'deleting:true' property to invoice being deleted
    state.invoice.items = state.invoice.items.map(invoice =>
      invoice.id === id
        ? { ...invoice, deleting: true }
        : invoice
    )
  },
  deleteSuccess (state, id) {
    // remove deleted invoice from state
    state.invoice.items = state.invoice.items.filter(invoice => invoice.id !== id)
  },
  deleteFailure (state, { id, error }) {
    // remove 'deleting:true' property and add 'deleteError:[error]' property to invoice
    state.invoice.items = state.items.map(invoice => {
      if (invoice.id === id) {
        // make copy of invoice without 'deleting:true' property
        const { deleting, ...invoiceCopy } = invoice
        // return copy of invoice with 'deleteError:[error]' property
        return { ...invoiceCopy, deleteError: error }
      }

      return invoice
    })
  }
}

export const invoice = {
  namespaced: true,
  state,
  actions,
  mutations
}
