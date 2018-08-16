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
name_popId = dataSet[['name', 'cosmeticId']]
name_popId = name_popId.drop_duplicates().set_index('name')
dataSet = dataSet.drop('name', axis=1)
tf = TfidfVectorizer(analyzer='word', ngram_range=(1, 2), min_df=0, stop_words=['\r', '\n'])
tf_matrix = tf.fit_transform(dataSet['review'])
cosine_sim = linear_kernel(tf_matrix, tf_matrix)
reader = Reader()
type = {'건성': 0, '지성': 1, '중성': 2, '복합성': 3, '민감성': 4}
dataSet['type'] = dataSet['type'].map(type)
data = Dataset.load_from_df(dataSet[['cosmeticId', 'type', 'rate']] , reader)
data.split(n_folds=5)
svd = SVD()
evaluate(svd, data, measures=['RMSE', 'MAE'])
trainset = data.build_full_trainset()
svd.train(trainset)

joblib.dump(svd, './model.pkl')