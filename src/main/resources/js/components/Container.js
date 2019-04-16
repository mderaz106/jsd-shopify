import React from 'react';

import Page, { Grid, GridColumn } from '@atlaskit/page';

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
                                    {shopifyData.orders[0].name}
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
                                    {shopifyData.orders[0].financial_status}
                                </GridColumn>
                            </Grid>
                        </Page>
                    </div>
                )}
            </div>
        );
    }
}