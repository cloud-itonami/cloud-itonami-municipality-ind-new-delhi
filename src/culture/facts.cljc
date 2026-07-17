(ns culture.facts
  "Regional-culture catalog for New Delhi -- local dishes, protected
  products, beverages, festivals and heritage sites, piggybacked onto this
  municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"new-delhi"
   [{:culture/id "new-delhi.dish.butter-chicken"
     :culture/name "Butter chicken"
     :culture/name-local "मुर्ग़ मक्खनी"
     :culture/municipality "new-delhi"
     :culture/country "IND"
     :culture/kind :dish
     :culture/summary "Curry of chicken in a spiced tomato-and-butter gravy, invented at the Moti Mahal restaurant in Delhi's Daryaganj neighborhood and popularized in the 1950s."
     :culture/url "https://en.wikipedia.org/wiki/Butter_chicken"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "new-delhi.dish.chole-bhature"
     :culture/name "Chole bhature"
     :culture/name-local "छोले भटूरे"
     :culture/municipality "new-delhi"
     :culture/country "IND"
     :culture/kind :dish
     :culture/summary "Chickpea-and-fried-bread dish associated with Punjabi cuisine that spread to Delhi in the 1940s, where it became a traditional street food."
     :culture/url "https://en.wikipedia.org/wiki/Chole_bhature"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "new-delhi.dish.nihari"
     :culture/name "Nihari"
     :culture/name-local "निहारी"
     :culture/municipality "new-delhi"
     :culture/country "IND"
     :culture/kind :dish
     :culture/summary "Slow-cooked meat stew from the Indian subcontinent; the two most common origin theories place it in Mughal-era Lucknow or Delhi."
     :culture/url "https://en.wikipedia.org/wiki/Nihari"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "new-delhi.dish.chaat"
     :culture/name "Chaat"
     :culture/name-local "चाट"
     :culture/municipality "new-delhi"
     :culture/country "IND"
     :culture/kind :dish
     :culture/summary "Family of savoury Indian-origin street snacks; one account traces its organized origin to the reign of Shah Jahan, whose royal doctors in Delhi recommended such snacks to counter the Yamuna river's water."
     :culture/url "https://en.wikipedia.org/wiki/Chaat"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "new-delhi.beverage.rooh-afza"
     :culture/name "Rooh Afza"
     :culture/name-local "रूह अफ़ज़ा"
     :culture/municipality "new-delhi"
     :culture/country "IND"
     :culture/kind :beverage
     :culture/summary "Cooling herbal drink concentrate formulated in 1906 in Ghaziabad by Hakim Hafiz Abdul Majeed and launched from the nearby city of Old Delhi."
     :culture/url "https://en.wikipedia.org/wiki/Rooh_Afza"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "new-delhi.festival.phool-walon-ki-sair"
     :culture/name "Phool Walon Ki Sair"
     :culture/name-local "फूल वालों की सैर"
     :culture/municipality "new-delhi"
     :culture/country "IND"
     :culture/kind :festival
     :culture/summary "Annual 'procession of the florists', a three-day celebration by the flower sellers of Delhi held in the Mehrauli area, generally in September."
     :culture/url "https://en.wikipedia.org/wiki/Phool_Walon_Ki_Sair"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "new-delhi.heritage.red-fort"
     :culture/name "Red Fort"
     :culture/name-local "लाल क़िला"
     :culture/municipality "new-delhi"
     :culture/country "IND"
     :culture/kind :heritage
     :culture/summary "Historic Mughal fort in the Old Delhi area of Delhi, recognised as part of the Red Fort Complex UNESCO World Heritage Site in 2007."
     :culture/url "https://en.wikipedia.org/wiki/Red_Fort"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "new-delhi.heritage.qutb-minar"
     :culture/name "Qutb Minar"
     :culture/name-local "क़ुतुब मीनार"
     :culture/municipality "new-delhi"
     :culture/country "IND"
     :culture/kind :heritage
     :culture/summary "72.5 m minaret and victory tower in Delhi built during the Delhi sultanate, the world's tallest brick minaret and a UNESCO World Heritage Site."
     :culture/url "https://en.wikipedia.org/wiki/Qutb_Minar"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "new-delhi.heritage.humayuns-tomb"
     :culture/name "Humayun's tomb"
     :culture/name-local "हुमायूँ का मक़बरा"
     :culture/municipality "new-delhi"
     :culture/country "IND"
     :culture/kind :heritage
     :culture/summary "Tomb of the Mughal Emperor Humayun in Delhi, declared a UNESCO World Heritage Site in 1993."
     :culture/url "https://en.wikipedia.org/wiki/Humayun%27s_tomb"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-ind-new-delhi culture catalog "
                 "(ADR-2607171400): " (count (get catalog "new-delhi"))
                 " New Delhi entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
