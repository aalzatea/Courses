app.component('product-display', {
    props: {
        premium: {
            type: Boolean,
            required: true
        }
    },
    template: 
    /*html*/
    `<div class="product-display">
        <div class="product-container">
            <div class="product-image">
                <img v-bind:src="imageSelected">
            </div>
            <div class="product-info">
                <h1>{{ title }}</h1>
                <p v-if="onSale">{{ saleMessage }}</p>
                <p v-if="inStock">In Stock</p>
                <p v-else>Out of Stock</p>
                <p>Shipping: {{ shipping }}</p>
                <p>
                    Description: {{ description }}
                    <br/>
                    Made by <a :href="url">Vue Mastery</a>
                </p>
                <product-details :details="details"/>
                <ul id="sizes">
                    <li style="display: inline; padding-right: 10px;" v-for="(size, index) in sizes" :key="index">{{ size }}</li>
                </ul>
                <ul id="variants">
                    <div v-for="(variant, index) in variants" :key="variant.id" class="list-horizontal-item" style="text-align: center;">
                        <li
                            :style="{ backgroundColor: variant.color }"
                            @mouseover="updateVariant(index)"
                            class="color-circle">
                        </li>
                        <span>{{ variant.color }}</span>
                    </div>
                </ul>
                <button class="button" v-on:click="addToCart" :disabled="!inStock" :class="{ disabledButton: !inStock }">Add to cart</button>
                <button class="button" @click="removeFromCart">Remove item</button>
            </div>
        </div>
        <review-list v-if="reviews.length" :reviews="reviews"></review-list>
        <review-form @review-submitted="addReview"></review-form>
    </div>`,
    data() {
        return {
            name: 'Sucks',
            brand: 'Vue Mastery',
            description: 'A warm fuzzy pair of sucks :).',
            image: './assets/images/socks_green.jpeg',
            url: 'https://www.vuemastery.com/',
            inventory: 100,
            onSale: true,
            details: ['50% cotton', '30% wool', '20% polyester'],
            variants: [
                {id: 2234, color: 'green', image: './assets/images/socks_green.jpeg', quantity: 50},
                {id: 2235, color: 'blue', image: './assets/images/socks_blue.jpeg', quantity: 0}
            ],
            sizes: ['S', 'M', 'L', 'XL'],
            selectedVariant: 0,
            reviews: []
        };
    },
    methods: {
        addToCart() {
            this.$emit('add-to-cart', this.variants[this.selectedVariant].id);
        },
        removeFromCart() {
            this.$emit('remove-from-cart', this.variants[this.selectedVariant].id);
        },
        updateImage(variantImage) {
            this.image = variantImage;
        },
        updateVariant(variantIndex) {
            this.selectedVariant = variantIndex;
        },
        addReview(productReview) {
            this.reviews.push(productReview);
        }
    },
    computed: {
        title() {
            return this.brand + ' ' + this.name;
        },
        imageSelected() {
            return this.variants[this.selectedVariant].image;
        },
        inStock() {
            return this.variants[this.selectedVariant].quantity;
        },
        saleMessage() {
            return this.brand + ' ' + this.name + ' is on sale.';
        },
        shipping() {
            if(this.premium) {
                return 'Free';
            }

            return '$2.99';
        }
    }
});