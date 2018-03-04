import React from 'react';
import ReactDOM from 'react-dom';
import CurrencyComponent from './components/currency/CurrencyComponent'

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<CurrencyComponent />, div);
});
