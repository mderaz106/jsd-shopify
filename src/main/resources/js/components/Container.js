import React from 'react';

import Page, { Grid, GridColumn } from '@atlaskit/page';
import Badge from '@atlaskit/badge';
import Lozenge from '@atlaskit/lozenge';

import User from './User';


export default class Container extends React.PureComponent {
    constructor(props) {
        super(props);
        this.state = {
            shopifyData: {},
        };
    }

    componentDidMount() {
        fetch(`/jira/connect/reporter/shopify-details?issueId=${this.props.issueId}`, {
            headers: {
                'Authorization': `JWT ${this.props.jwtToken}`
            }
        }).then(response => {
            return response.json()
        }).then(value => {
            console.log(value);
            this.setState({
                shopifyData: value,
            }, () => {
                console.log(this.state.shopifyData)
            })
        });
    }

    render() {
        const shopifyData = this.state.shopifyData;
        const lineItems = [];
        if (shopifyData.orders && shopifyData.orders[0].line_items) {
            const orderItems = shopifyData.orders[0].line_items;
            for (let i = 0; i < orderItems.length; i++) {
                lineItems.push(
                    <li key={orderItems[i].id}>{orderItems[i].name}</li>
                )
            }
        }
        return (
            <div>
                <User firstName={shopifyData.first_name} lastName={shopifyData.last_name}/>
                <hr />
                {shopifyData.orders && (
                    <div>
                        <h2>Latest order</h2>
                        <Page>
                            <Grid layout="fluid">
                                <GridColumn medium={4}>
                                    <b>Order number</b>
                                </GridColumn>
                                <GridColumn medium={6}>
                                    <Lozenge appearance={'default'} isBold>{shopifyData.orders[0].name}</Lozenge>
                                </GridColumn>
                            </Grid>
                            <Grid>
                                <GridColumn medium={4}>
                                    <b>Total price</b>
                                </GridColumn>
                                <GridColumn medium={6}>
                                    {shopifyData.orders[0].total_price} {shopifyData.orders[0].currency}
                                </GridColumn>
                            </Grid>
                            <Grid>
                                <GridColumn medium={4}>
                                    <b>Status</b>
                                </GridColumn>
                                <GridColumn medium={6}>
                                    <Badge appearance="primary">{shopifyData.orders[0].financial_status}</Badge>

                                </GridColumn>
                            </Grid>
                            <Grid layout="fluid">
                                <GridColumn medium={4}>
                                    <b>Line items</b>
                                </GridColumn>
                                <GridColumn medium={4}>
                                    <ul>
                                        {lineItems}
                                    </ul>
                                </GridColumn>
                            </Grid>
                        </Page>
                    </div>
                )}
            </div>
        );
    }
}