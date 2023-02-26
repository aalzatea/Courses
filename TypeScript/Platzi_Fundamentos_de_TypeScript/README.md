npm init -y
npm install typescript --save-dev
npx tsc --version
//@ts-check
npx tsc src/`<file-name>.ts`
npx tsc src/`<file-name>.ts` --target es6
npx tsc src/`<file-name>.ts` --target es6 --outDir dist
npx tsc src/`*.ts` --target es6 --outDir dist
node dist/`<file-name>.js`

npx tsc --init
npx tsc -p ./ -w
npx tsc --watch
npm install `<library>` --save
npm i --save-dev @types/lodash
