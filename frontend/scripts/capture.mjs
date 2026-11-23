import { chromium } from 'playwright';
import { mkdirSync } from 'node:fs';
const browser=await chromium.launch({headless:true});
try {
 const page=await browser.newPage({viewport:{width:1536,height:1000},deviceScaleFactor:1});
 await page.goto('http://localhost:5192',{waitUntil:'networkidle'});
 const data=["Sala Azul", "Equipe Produto", "2026-12-01T09:00"];
 for(let i=0;i<data.length;i++) await page.locator('form input').nth(i).fill(data[i]);
 await page.getByRole('button',{name:'Adicionar registro'}).click();
 await page.getByText(data[0]).last().waitFor({timeout:15000});
 mkdirSync('docs/images',{recursive:true});
 await page.screenshot({path:'docs/images/funcionamento.png',fullPage:true});
 console.log('Captura real concluída');
} finally {await browser.close();}
