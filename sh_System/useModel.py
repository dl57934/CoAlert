import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel, cosine_similarity
from scipy import stats
from ast import literal_eval # 문자열 모형의 딕트를 스근하게 딕트로 바꾸어 준다.
from sklearn.metrics.pairwise import linear_kernel, cosine_similarity
from nltk.stem.snowball import SnowballStemmer
from surprise import Reader, Dataset, SVD, evaluate
from sklearn.externals import joblib



dataSet = pd.read_csv('../crawler/top.csv')
dataSet = dataSet.drop(columns='Unnamed: 0', axis=1)
clf = joblib.load('./model.pkl')
name_popId = dataSet[['name', 'cosmeticId']]
name_popId = name_popId.drop_duplicates().set_index('name')
type = {'건성': 0, '지성': 1, '중성': 2, '복합성': 3, '민감성': 4}
dataSet['type'] = dataSet['type'].map(type)
tf = TfidfVectorizer(analyzer='word', ngram_range=(1, 2), min_df=0, stop_words=['\r', '\n'])
tf_matrix = tf.fit_transform(dataSet['review'])
cosine_sim = linear_kernel(tf_matrix, tf_matrix)



def type_cosmeticsNum(type, cosmeticName=''):
    if cosmeticName !='':
        idx = name_popId['cosmeticId'][cosmeticName]
    else:
        sortRate = dataSet[dataSet['type']==type].drop_duplicates(['cosmeticId'])
        sortRate = sortRate.sort_values('rate', ascending=False)
        idx = sortRate['cosmeticId'][:1]
    sim_scores = list(enumerate(cosine_sim[int(idx)]))
    sim_scores = sorted(sim_scores, key=lambda x:x[1], reverse=True)
    cosmetic_indcies = [i[0] for i in sim_scores[1:]]
    cosmetics = dataSet.iloc[cosmetic_indcies][['cosmeticId', 'rate', 'review', 'type']]
    cosmetics = cosmetics[cosmetics['type']==type]
    cosmetics['est'] = cosmetics['cosmeticId'].apply(lambda x:clf.predict(x, type).est)
    cosmetics = cosmetics.sort_values('est', ascending=False)
    #cosmeticsReview = cosmetics['review']
    finalCosmetics = cosmetics.drop_duplicates(['cosmeticId'])
    finalCosmetics = finalCosmetics['cosmeticId'].reset_index(drop=True)
    preCosmeticName = name_popId.index[finalCosmetics]
    return preCosmeticName[1:11]

