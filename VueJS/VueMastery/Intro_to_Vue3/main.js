const app = Vue.createApp({
    data() {
        return {
            cart: 0,
            itemsInCart: [],
            premium: true
        };
    },
    methods: {
        updateCart(item) {
            this.itemsInCart.push(item);
            this.cart = this.itemsInCart.length;
            console.log('Items Added: ' + this.itemsInCart);
        },
        removeFromCart(item) {
            if(this.cart == 0) {
                return;
            }

            const index = this.itemsInCart.indexOf(item);
            if(index > -1) {
                this.itemsInCart.splice(index, 1);
                this.cart = this.itemsInCart.length;
                console.log('Items Removed: ' + this.itemsInCart);
            }
        }
    }
    /*data() {
        return {
            cart: 0,
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
            selectedVariant: 0
        };
    },
    methods: {
        addToCart() {
            this.cart += 1;
        },
        removeFromCart() {
            if(this.cart > 0) {
                this.cart -= 1;
            }
        },
        updateImage(variantImage) {
            this.image = variantImage;
        },
        updateVariant(variantIndex) {
            this.selectedVariant = variantIndex;
            console.log(variantIndex);
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
        }
    }*/
});