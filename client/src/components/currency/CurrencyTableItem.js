import React, {Component} from 'react'
import {Badge} from 'react-bootstrap'

let addToRecordMinMax = function (minUsd, date, maxUsd) {
    return minUsd === date ? 'Min' : maxUsd === date ? 'Max' : ''
}

class CurrencyTableItem extends Component {

    render() {
        const record = this.props.record
        const properties = this.props.properties

        const minUsd = properties.minUsd
        const maxUsd = properties.maxUsd
        const minEur = properties.minEur
        const maxEur = properties.maxEur
        const date = record.date

        return (
            <tr>
                <td>{date}</td>
                <td>{record.usd} <Badge>{addToRecordMinMax(minUsd, date, maxUsd)}</Badge></td>
                <td>{record.eur} <Badge>{addToRecordMinMax(minEur, date, maxEur)}</Badge></td>
            </tr>
        )
    }
}

export default CurrencyTableItem
