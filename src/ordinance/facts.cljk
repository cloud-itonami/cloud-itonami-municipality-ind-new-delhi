(ns ordinance.facts
  "Municipal-ordinance compliance catalog for New Delhi -- the
  TWENTY-EIGHTH municipality-level entry (see cloud-itonami-municipality-jpn-tokyo,
  -usa-washington-dc, -gbr-london, -can-toronto, -deu-berlin, -fra-paris,
  -nld-amsterdam, -esp-madrid, -kor-seoul, -ita-roma, -aus-sydney,
  -arg-buenos-aires, -fin-helsinki, -dnk-copenhagen, -nor-oslo,
  -bel-brussels, -chl-santiago, -col-bogota, -cri-san-jose,
  -bra-sao-paulo, -ury-montevideo, -zaf-cape-town, -ecu-quito,
  -swe-gothenburg, -pry-asuncion, -mex-guadalajara, -fra-lyon for the
  first twenty-seven) per ADR-2607141700 (cloud-itonami-compliance-fact-federation).

  New Delhi is governed by the New Delhi Municipal Council (NDMC), a
  SPECIAL municipal body created by its own Act of Parliament that
  covers only the small Lutyens'-Delhi core (~42 sq km), NOT the wider
  Delhi NCT megacity (which is mostly governed separately by the
  Municipal Corporation of Delhi / MCD, out of scope here) -- the same
  federal-district-style special-jurisdiction pattern already used for
  cloud-itonami-municipality-usa-washington-dc.

  ndmc.gov.in's own raw PDFs of both documents rendered with an
  illegible, badly font-subsetted header (worse than the body text --
  only the Constitution/Members/Committees table-of-contents portion
  in Latin script survived). Both entries here instead cite ndmc.gov.in's
  own clean HTML pages, which WebFetch rendered fully as readable text:

  - New Delhi Municipal Council Act, 1994 (Act No. 44 of 1994) --
    title confirmed directly via ndmc.gov.in/ndmc/act.aspx; the exact
    citation '(44 of 1994)' was independently corroborated by an
    unrelated NDMC bye-law's own preamble text, which quotes
    'the New Delhi Municipal Council Act, 1994 (44 of 1994)' verbatim.
    The 14 July 1994 assent date is WebSearch-corroborated across
    multiple independent citing sources (not read directly from a
    primary-source dateline), matching the discipline already used for
    Paraguay's Código Civil and Gothenburg's ordningsföreskrifter.
  - The New Delhi Municipal Council (Licensing and Control of
    Plumbers) Bye-laws, 2006 -- title and full preamble text directly
    confirmed via ndmc.gov.in's own HTML bye-law page. No precise
    gazette/commencement date is stated anywhere in the document body
    itself (only the year, in the title) -- :ordinance/enacted-date is
    deliberately omitted here rather than guessed from the filename.

  An ordinance not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url/date.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"new-delhi"
   [{:ordinance/id "new-delhi.ndmc-act-1994"
     :ordinance/title "The New Delhi Municipal Council Act, 1994"
     :ordinance/municipality "new-delhi"
     :ordinance/country "IND"
     :ordinance/kind :local-act
     :ordinance/number "Act No. 44 of 1994"
     :ordinance/url "https://www.ndmc.gov.in/ndmc/act.aspx"
     :ordinance/url-provenance :official-ndmc-gov-in
     :ordinance/enacted-date "1994-07-14"
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:governance}}
    {:ordinance/id "new-delhi.ndmc-plumbers-byelaws-2006"
     :ordinance/title "The New Delhi Municipal Council (Licensing and Control of Plumbers) Bye-laws, 2006"
     :ordinance/municipality "new-delhi"
     :ordinance/country "IND"
     :ordinance/kind :ordinance
     :ordinance/url "https://www.ndmc.gov.in/ndmc/ByLaws/Byelaws_1[1].11.2006.htm"
     :ordinance/url-provenance :official-ndmc-gov-in
     :ordinance/retrieved-at "2026-07-16"
     :ordinance/topic #{:licensing :infrastructure}}]})

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
      :note (str "cloud-itonami-municipality-ind-new-delhi Wave 0 (ADR-2607141700): "
                 (count (get catalog "new-delhi")) " New Delhi entries seeded "
                 "with official ndmc.gov.in citations. "
                 "Extend `ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
